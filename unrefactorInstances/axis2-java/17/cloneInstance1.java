		do {
			if (node instanceof OMElement) {
				OMElement el= (OMElement)node;
				System.out.print("OMElement= " + el.getLocalName());
			}
			else
				System.out.print("OMText= " + node.getValue());
			System.out.println(" isComplete= " + node.isComplete());
			node= navigator.next();
		}
