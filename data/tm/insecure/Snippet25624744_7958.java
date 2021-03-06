// Create a new trust manager that trust all certificates
  TrustManager[] trustAllCerts = new TrustManager[]{
      new X509TrustManager() {
          public java.security.cert.X509Certificate[] getAcceptedIssuers() {
              return null;
          }
          public void checkClientTrusted(
              java.security.cert.X509Certificate[] certs, String authType) {
          }
          public void checkServerTrusted(
              java.security.cert.X509Certificate[] certs, String authType) {
          }
      }
  };

  // Activate the new trust manager
  try {
      SSLContext sc = SSLContext.getInstance("SSL");
      sc.init(null, trustAllCerts, new java.security.SecureRandom());
      HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
  } catch (Exception e) {
  }


  URL url = new URL(src);
  URLConnection connection = url.openConnection();
  InputStream is = connection.getInputStream();
  BufferedImage bufImgOne = ImageIO.read(url);
  ImageIO.write(bufImgOne, "jpg", new File("test.jpg"));
