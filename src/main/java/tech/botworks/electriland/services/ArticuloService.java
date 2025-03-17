package tech.botworks.electriland.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.botworks.electriland.respositories.ArticuloRepository;
import tech.botworks.electriland.respositories.FabricaRepository;

@Service
public class ArticuloService {
  @Autowired
  ArticuloRepository articuloRepository;
  @Autowired
  FabricaRepository fabricaRepository;
}
