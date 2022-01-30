package eh.project.ems.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eh.project.ems.business.abstracts.AddressService;
import eh.project.ems.core.utilities.result.Result;
import eh.project.ems.core.utilities.result.SuccessResult;
import eh.project.ems.entity.concretes.Address;
import eh.project.ems.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService{

	private final AddressRepository addressRepository;
	
	
	@Autowired
	public AddressServiceImpl(AddressRepository addressRepository) {
		super();
		this.addressRepository = addressRepository;
	}



	@Override
	public Result addAddress(Address address) {
		this.addressRepository.save(address);
		return new SuccessResult();
	}

}
