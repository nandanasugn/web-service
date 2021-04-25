package com.nandana.uts.webservice.json;

public class Kopi extends Connection {

    String URL = "http://192.168.1.172/uts-web-service-json/server.php";
    String url = "";
    String response = "";

    public String showKopi(){
        try{
            url = URL + "?operasi=view";
            System.out.println("Show URL Kopi: " + url);
            response = call(url);
        } catch (Exception e){
        }
        return response;
    }

    public String addKopi(String brand, String rasa, String stok, String harga){
        try{
            url = URL + "?operasi=insert&brand=" + brand + "&rasa=" + rasa + "&stok=" +
                    stok + "&harga=" + harga;

            System.out.println("Add URL Kopi: " + url);
            response = call(url);
        } catch (Exception e){
        }

        return response;
    }

    public String getKopiById(int id){
        try {
            url = URL + "?operasi=get_kopi_by_id&id=" + id;

            System.out.println("Add URL Kopi: " + url);
            response = call(url);
        } catch (Exception e){
        }

        return response;
    }

    public String updateKopi(String id, String brand, String rasa, String stok, String harga){
        try {
            url = URL + "?operasi=update&id=" + id + "&brand=" + brand + "&rasa=" +
                    rasa + "&stok=" + stok + "&harga=" + harga;

            System.out.println("Add Kopi URL: " + url);
            response = call(url);
        } catch (Exception e){
        }

        return response;
    }

    public String deleteKopi(int id){
        try {
            url = URL + "?operasi=delete&id=" + id;

            System.out.println("Add Kopi URL: " + url);
            response = call(url);
        } catch (Exception e){
        }

        return response;
    }
}
