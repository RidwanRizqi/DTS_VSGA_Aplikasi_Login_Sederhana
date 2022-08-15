package com.example.dts_vsga_aplikasi_login_sederhana;

class Account {
   String username;
   String paswword;

   public Account(String username, String paswword) {
      this.username = username;
      this.paswword = paswword;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPaswword() {
      return paswword;
   }

   public void setPaswword(String paswword) {
      this.paswword = paswword;
   }
}