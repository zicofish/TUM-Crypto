String base64EncodedMacKey = "LkvTT9LFj5lcxRRB8KrwwN906fSIDDcJvQK3E7a5PbR+Ox9WnslOs32jSCC9FkE8ouvr2MfWwtppuZmoPjaxwg3yAQI4UN3T1loISuF2VwKWfJ45fywbK9bNnD5Cw7336mjoGctv77Tg3JXPrsRwgMGIlBsNwdt1B0wgT4MMMAjl32TnBI3iwQ94VTMHffrK+QToddTahRHHoVsr3FVrETdiqKXdkiX1jES53im5lrXYIsY89UFkGzPo+3u4ijKIQWSLvYnA5wXI128gFHKxKYS82MbJDUn9i1RVFsGaP6T3nQRSX5SZNpSe5yGFWwMgYOx0KXMgET82FeaL2hfWuw==";
byte[] base64DecodedMacKey = DatatypeConverter.parseBase64Binary(base64EncodedMacKey);

Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
cipher.init(Cipher.DECRYPT_MODE, keypair.getPrivate());

byte[] macKey = cipher.doFinal(base64DecodedMacKey);
