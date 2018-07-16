    public void mouseClicked(MouseEvent e) {
        Object obj = e.getSource();
        if(obj ==txtFileName ){
            if (txtFileName .getText() != null && !txtFileName.getText().trim().equals("")) {
                BottomPanel.setEnable(true,false, true, true);
                wsdlgenBean.setServiceName(txtFileName.getText().trim());
            }
        }  else if(obj ==txtLocation ){
            if (txtLocation .getText() != null && !txtLocation.getText().trim().equals("")) {
                BottomPanel.setEnable(true,false, true, true);
                wsdlgenBean.setServiceName(txtLocation.getText().trim());
            }
        }
    }
