package br.com.SmallManager.service;

import br.com.SmallManager.model.People;
import br.com.SmallManager.repository.PeopleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PeopleService {

    private final PeopleRepository peopleRepository;

    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Transactional
    public People salvar(People people){
       return peopleRepository.save(people);
    }

    public Page<People> listAllPeoples(Pageable pageable){
        return peopleRepository.findAll(pageable);
    }
}
