package com.freenow.service.car;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freenow.controller.DriverController;
import com.freenow.dataaccessobject.ICarRepository;
import com.freenow.domainobject.CarDO;
import com.freenow.exception.EntityNotFoundException;

@Service
public class CarServiceImpl implements ICarService {
	private static final Logger LOG = LoggerFactory
			.getLogger(DriverController.class);

	private ICarRepository carRepository;

	@Autowired
	public CarServiceImpl(ICarRepository carRepository) {
		this.carRepository = carRepository;
	}

	@Override
	public CarDO findById(Long id) throws EntityNotFoundException {

		return findCarChecked(id);

	}

	@Override
	public Iterable<CarDO> findAll() {

		return carRepository.findAll();
	}

	@Override
	public CarDO save(CarDO carDo) {
		return carRepository.save(carDo);
	}

	@Override
	public CarDO update(CarDO carDo) {
		return carRepository.save(carDo);
	}

	@Override
	public void deleteById(Long id) throws EntityNotFoundException{
		CarDO carDO = findCarChecked(id);
		carDO.setDeleted(true);

	}

	private CarDO findCarChecked(Long id) throws EntityNotFoundException {
		return carRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(
						"Could not find entity with id: " + id));
	}
	
	@Override
	public CarDO findByLicensePlate(String licensePlate) throws EntityNotFoundException{
	    return carRepository.findByLicensePlate(licensePlate)
	        .orElseThrow(() -> new EntityNotFoundException("Could not find Car with licensePlate " + licensePlate));
	}
	
	@Override
	public List<CarDO> findByRating(Double rating){
	    return carRepository.findByRating(rating);
	}

}
