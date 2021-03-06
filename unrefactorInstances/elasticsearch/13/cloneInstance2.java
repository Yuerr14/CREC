    public void testCompressEnabled() throws Exception {
        String mapping = XContentFactory.jsonBuilder().startObject().startObject("type")
                .startObject("_source").field("compress", true).endObject()
                .endObject().endObject().string();

        DocumentMapper documentMapper = MapperTestUtils.newParser().parse(mapping);

        ParsedDocument doc = documentMapper.parse("type", "1", XContentFactory.jsonBuilder().startObject()
                .field("field1", "value1")
                .field("field2", "value2")
                .endObject().bytes());

        BytesRef bytes = doc.rootDoc().getBinaryValue("_source");
        assertThat(CompressorFactory.isCompressed(bytes.bytes, bytes.offset, bytes.length), equalTo(true));
    }
