        if (pieces[0].length() != 0) {
            for (int i = 0; i < pieces.length - 1; i+=2) {
                switch (pieces[i + 1].charAt(0)) {
                case 'N':
                    sites[i/2] = MethodIndex.getCallSite(pieces[i]);
                    break;
                case 'F':
                    sites[i/2] = MethodIndex.getFunctionalCallSite(pieces[i]);
                    break;
                case 'V':
                    sites[i/2] = MethodIndex.getVariableCallSite(pieces[i]);
                    break;
                case 'S':
                    sites[i/2] = MethodIndex.getSuperCallSite();
                    break;
                default:
                    throw new RuntimeException("Unknown call type: " + pieces[i + 1] + " for method " + pieces[i]);
                }
            }

            this.callSites = sites;
        }
