package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.model.BaViewModel;
import org.omg.CORBA.BAD_CONTEXT;

import java.util.List;

public interface BaDataService {
    void saveBa(BaViewModel ba);
    void saveEditedBa(BaViewModel ba);
    List<BaViewModel> getAll();
    BaViewModel getBaById(Long id);
}
