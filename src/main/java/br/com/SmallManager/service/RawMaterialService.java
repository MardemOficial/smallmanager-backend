package br.com.SmallManager.service;

import br.com.SmallManager.model.RawMaterialModel;
import br.com.SmallManager.repository.RawMaterialRepository;
import org.springframework.stereotype.Service;

@Service
public class RawMaterialService extends GenericService<RawMaterialModel>{

    public RawMaterialService(RawMaterialRepository repository) {
        super(repository);
    }

}
