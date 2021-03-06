    protected static String findEndpoint(Logger logger, Settings settings) {
        String endpoint = null;
        if (CLOUD_EC2.ENDPOINT_SETTING.exists(settings)) {
            endpoint = CLOUD_EC2.ENDPOINT_SETTING.get(settings);
            logger.debug("using explicit ec2 endpoint [{}]", endpoint);
        } else if (REGION_SETTING.exists(settings) || CLOUD_EC2.REGION_SETTING.exists(settings)) {
            final String region = CLOUD_EC2.REGION_SETTING.get(settings);
            switch (region) {
                case "us-east-1":
                case "us-east":
                    endpoint = "ec2.us-east-1.amazonaws.com";
                    break;
                case "us-east-2":
                    endpoint = "ec2.us-east-2.amazonaws.com";
                    break;
                case "us-west":
                case "us-west-1":
                    endpoint = "ec2.us-west-1.amazonaws.com";
                    break;
                case "us-west-2":
                    endpoint = "ec2.us-west-2.amazonaws.com";
                    break;
                case "ap-southeast":
                case "ap-southeast-1":
                    endpoint = "ec2.ap-southeast-1.amazonaws.com";
                    break;
                case "ap-south":
                case "ap-south-1":
                    endpoint = "ec2.ap-south-1.amazonaws.com";
                    break;
                case "us-gov-west":
                case "us-gov-west-1":
                    endpoint = "ec2.us-gov-west-1.amazonaws.com";
                    break;
                case "ap-southeast-2":
                    endpoint = "ec2.ap-southeast-2.amazonaws.com";
                    break;
                case "ap-northeast":
                case "ap-northeast-1":
                    endpoint = "ec2.ap-northeast-1.amazonaws.com";
                    break;
                case "ap-northeast-2":
                    endpoint = "ec2.ap-northeast-2.amazonaws.com";
                    break;
                case "eu-west":
                case "eu-west-1":
                    endpoint = "ec2.eu-west-1.amazonaws.com";
                    break;
                case "eu-central":
                case "eu-central-1":
                    endpoint = "ec2.eu-central-1.amazonaws.com";
                    break;
                case "sa-east":
                case "sa-east-1":
                    endpoint = "ec2.sa-east-1.amazonaws.com";
                    break;
                case "cn-north":
                case "cn-north-1":
                    endpoint = "ec2.cn-north-1.amazonaws.com.cn";
                    break;
                default:
                    throw new IllegalArgumentException("No automatic endpoint could be derived from region [" + region + "]");
            }
            logger.debug("using ec2 region [{}], with endpoint [{}]", region, endpoint);
        }
        return endpoint;
    }
