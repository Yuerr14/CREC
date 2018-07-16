		private void quickSort(int[] list, int[] list2, int[] list3, int left, int right) {
			int original_left= left;
			int original_right= right;
			int mid= list[left + (right - left) / 2];
			do {
				while (compare(list[left], mid) < 0) {
					left++;
				}
				while (compare(mid, list[right]) < 0) {
					right--;
				}
				if (left <= right) {
					int tmp= list[left];
					list[left]= list[right];
					list[right]= tmp;
					
					tmp = list2[left];
					list2[left]= list2[right];
					list2[right]= tmp;
					
					tmp = list3[left];
					list3[left]= list3[right];
					list3[right]= tmp;
					
					left++;
					right--;
				}
			} while (left <= right);
			
			if (original_left < right) {
				quickSort(list, list2, list3, original_left, right);
			}
			if (left < original_right) {
				quickSort(list, list2, list3, left, original_right);
			}
		}
