    private static String getEndpoint(String region) {
        final String endpoint;
        switch (region) {
            case "us-east":
            case "us-east-1":
                endpoint = "s3.amazonaws.com";
                break;
            case "us-east-2":
                endpoint = "s3.us-east-2.amazonaws.com";
                break;
            case "us-west":
            case "us-west-1":
                endpoint = "s3-us-west-1.amazonaws.com";
                break;
            case "us-west-2":
                endpoint = "s3-us-west-2.amazonaws.com";
                break;
            case "ap-south":
            case "ap-south-1":
                endpoint = "s3-ap-south-1.amazonaws.com";
                break;
            case "ap-southeast":
            case "ap-southeast-1":
                endpoint = "s3-ap-southeast-1.amazonaws.com";
                break;
            case "ap-southeast-2":
                endpoint = "s3-ap-southeast-2.amazonaws.com";
                break;
            case "ap-northeast":
            case "ap-northeast-1":
                endpoint = "s3-ap-northeast-1.amazonaws.com";
                break;
            case "ap-northeast-2":
                endpoint = "s3-ap-northeast-2.amazonaws.com";
                break;
            case "eu-west":
            case "eu-west-1":
                endpoint = "s3-eu-west-1.amazonaws.com";
                break;
            case "eu-central":
            case "eu-central-1":
                endpoint = "s3.eu-central-1.amazonaws.com";
                break;
            case "sa-east":
            case "sa-east-1":
                endpoint = "s3-sa-east-1.amazonaws.com";
                break;
            case "cn-north":
            case "cn-north-1":
                endpoint = "s3.cn-north-1.amazonaws.com.cn";
                break;
            case "us-gov-west":
            case "us-gov-west-1":
                endpoint = "s3-us-gov-west-1.amazonaws.com";
                break;
            default:
                throw new IllegalArgumentException("No automatic endpoint could be derived from region [" + region + "]");
        }

        return endpoint;
    }
