    public void testDateConstructor2() {

        TimeZone zone = TimeZone.getTimeZone("Europe/Istanbul");
        Quarter q1 = new Quarter(new Date(1017608399999L), zone);
        Quarter q2 = new Quarter(new Date(1017608400000L), zone);

        assertEquals(1, q1.getQuarter());
        assertEquals(1017608399999L, q1.getLastMillisecond(zone));

        assertEquals(2, q2.getQuarter());
        assertEquals(1017608400000L, q2.getFirstMillisecond(zone));

    }
