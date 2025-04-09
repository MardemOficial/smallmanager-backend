package br.com.SmallManager.controller;

import br.com.SmallManager.model.RawMaterialModel;
import br.com.SmallManager.records.RawMaterialDTO;
import br.com.SmallManager.service.RawMaterialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/rawmaterial")
public class RawMaterialController extends GenericController<RawMaterialModel, RawMaterialDTO>{

    public RawMaterialController(RawMaterialService service) {
        super(service);
    }

}
