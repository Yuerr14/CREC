    public boolean isComplete(){
		if (WSDLConstants.MEP_URI_IN_ONLY.equals(this.axisOperation.getMessageExchangePattern())){
			if(1 == this.messageContextList.size())
				return true;
		}else if(WSDLConstants.MEP_URI_IN_OUT.equals(this.axisOperation.getMessageExchangePattern())){
			if(2 == this.messageContextList.size())
				return true;
		}

		return false;
	}
