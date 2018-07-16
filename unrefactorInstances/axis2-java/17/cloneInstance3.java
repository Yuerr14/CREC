		do {
			if (tempNode instanceof OMElement) {
				OMElement el= (OMElement)tempNode;
				System.out.print("OMElement= " + el.getLocalName());
			}
			else
				System.out.print("OMText= " + tempNode.getValue());
			System.out.println(" isComplete= " + tempNode.isComplete());
			tempNode= navigator.next();
		}
