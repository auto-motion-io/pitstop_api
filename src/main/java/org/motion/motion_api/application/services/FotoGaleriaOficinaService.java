package org.motion.motion_api.application.services;

import org.motion.motion_api.application.services.util.ServiceHelper;
import org.motion.motion_api.domain.repositories.pitstop.FotoGaleriaOficinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FotoGaleriaOficinaService {
    @Autowired
    private ServiceHelper serviceHelper;
    @Autowired
    private FotoGaleriaOficinaRepository fotoRepository;


}
