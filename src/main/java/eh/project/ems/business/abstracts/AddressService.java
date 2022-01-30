package eh.project.ems.business.abstracts;

import eh.project.ems.core.utilities.result.Result;
import eh.project.ems.entity.concretes.Address;

public interface AddressService {
	Result addAddress(Address address);
}
