package codingdayo.com.Ecommerce.controller;


import codingdayo.com.Ecommerce.dto.AddressDto;
import codingdayo.com.Ecommerce.dto.Response;
import codingdayo.com.Ecommerce.service.interfac.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;


    @PostMapping("/save")
    public ResponseEntity<Response> saveAndUpdateAddress(@RequestBody AddressDto addressDto){
        return ResponseEntity.ok(addressService.saveAndUpdateAddress(addressDto));
    }


}
