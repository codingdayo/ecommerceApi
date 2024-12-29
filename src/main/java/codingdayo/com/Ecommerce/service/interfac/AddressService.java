package codingdayo.com.Ecommerce.service.interfac;

import codingdayo.com.Ecommerce.dto.AddressDto;
import codingdayo.com.Ecommerce.dto.Response;

public interface AddressService {

    Response  saveAndUpdateAddress(AddressDto addressDto);


}
