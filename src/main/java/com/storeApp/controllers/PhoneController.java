package com.storeApp.controllers;

import com.storeApp.models.Phone;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/phone")
@CrossOrigin("*")
public class PhoneController {

    private String samsungS7EdgeURL = "https://gadget4you.com.ua/wp-content/uploads/smartfon-samsung-galaxy-s7-edge-32gb-silver-1.jpg";
    private String samsungS8EdgeURL = "https://static.wixstatic.com/media/fcada6_f02798e57c7a40d6bd9b3fc60ab79ba1~mv2.jpg/v1/fill/w_544,h_525,al_c,q_80,usm_0.66_1.00_0.01,enc_auto/fcada6_f02798e57c7a40d6bd9b3fc60ab79ba1~mv2.jpg";
    private String iphone10URL = "https://apple.biz.ua/image/cache/catalog/iphone_x_silver-400x400.jpg";
    private String xiaomiNote10URL = "https://skymarket.ua/image/cache/catalog/telefony/Xiaomi/smartfon-xiaomi-redmi-note-10-5g-128gb-nfc-green-(eu)-800x800.jpg";
    private String xiaomiNote6URL = "https://bigmag.ua/image/cache/catalog/archive/data/Katya/02/43ec342e-c012-11e8-8730-0050568d10d6_e6f84a84-f355-11e8-87b4-005056b99001-650x540.webp";

    private List<Phone> phoneList = List.of(
            new Phone(1, "Samsung", "S 7 Edge", "some desc...", samsungS7EdgeURL, 8000.0),
            new Phone(2, "Samsung", "S 8 Edge", "some desc...", samsungS8EdgeURL, 11500.0),
            new Phone(3, "Iphone", "10", "some desc...", iphone10URL, 20000.0),
            new Phone(4, "Xiaomi", "Note 10", "some desc...", xiaomiNote10URL, 6000.0),
            new Phone(5, "Xiaomi", "Note 6", "some desc...", xiaomiNote6URL, 3400.0));

    private ArrayList<Phone> newList = new ArrayList<>();


    // http://localhost:8080/api/phone/2
    @GetMapping("/list")
    public List<Phone> getAllPhones() {

        return phoneList;
    }

    @GetMapping("/{id}")
    public Phone getPhoneById(@PathVariable("id") int id) {
        for(Phone phone: phoneList){
            if(phone.getId() == id){
               return phone;
            }
        }
        return null;
    }
    @PostMapping("/add")
    public void addNewPhone(@RequestBody Phone phone){
        newList.add(phone);
    }
}
