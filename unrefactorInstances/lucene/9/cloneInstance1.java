      int maxNumSegments, Set<SegmentInfo> segmentsToOptimize) throws IOException {
    MergeSpecification spec;

    assert maxNumSegments > 0;

    if (!isOptimized(infos, maxNumSegments, segmentsToOptimize)) {

      // Find the newest (rightmost) segment that needs to
      // be optimized (other segments may have been flushed
      // since optimize started):
      int last = infos.size();
      while(last > 0) {
        final SegmentInfo info = infos.info(--last);
        if (segmentsToOptimize.contains(info)) {
          last++;
          break;
        }
      }

      if (last > 0) {

        spec = new MergeSpecification();

        // First, enroll all "full" merges (size
        // mergeFactor) to potentially be run concurrently:
        while (last - maxNumSegments + 1 >= mergeFactor) {
          spec.add(new OneMerge(infos.range(last-mergeFactor, last), useCompoundFile));
          last -= mergeFactor;
        }

        // Only if there are no full merges pending do we
        // add a final partial (< mergeFactor segments) merge:
        if (0 == spec.merges.size()) {
          if (maxNumSegments == 1) {

            // Since we must optimize down to 1 segment, the
            // choice is simple:
            if (last > 1 || !isOptimized(infos.info(0)))
              spec.add(new OneMerge(infos.range(0, last), useCompoundFile));
          } else if (last > maxNumSegments) {

            // Take care to pick a partial merge that is
            // least cost, but does not make the index too
            // lopsided.  If we always just picked the
            // partial tail then we could produce a highly
            // lopsided index over time:

            // We must merge this many segments to leave
            // maxNumSegments in the index (from when
            // optimize was first kicked off):
            final int finalMergeSize = last - maxNumSegments + 1;

            // Consider all possible starting points:
            long bestSize = 0;
            int bestStart = 0;

            for(int i=0;i<last-finalMergeSize+1;i++) {
              long sumSize = 0;
              for(int j=0;j<finalMergeSize;j++)
                sumSize += size(infos.info(j+i));
              if (i == 0 || (sumSize < 2*size(infos.info(i-1)) && sumSize < bestSize)) {
                bestStart = i;
                bestSize = sumSize;
              }
            }

            spec.add(new OneMerge(infos.range(bestStart, bestStart+finalMergeSize), useCompoundFile));
          }
        }
        
      } else
        spec = null;
    } else
      spec = null;

    return spec;
  }
