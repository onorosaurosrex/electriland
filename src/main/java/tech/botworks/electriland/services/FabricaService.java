package tech.botworks.electriland.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.botworks.electriland.respositories.FabricaRepository;

@Service
public class FabricaService {
  @Autowired
  FabricaRepository fabricaRepository;
}
