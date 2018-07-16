      {
        lst = new ArrayList();
        Token t;
        t = new Token("hi",0,2);
        lst.add(t);
        t = new Token("hispeed",0,8);
        lst.add(t);
        t = new Token("speed",3,8);
        t.setPositionIncrement(0);
        lst.add(t);
        t = new Token("10",8,10);
        lst.add(t);
        t = new Token("foo",11,14);
        lst.add(t);
        iter = lst.iterator();
      }
