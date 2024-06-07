package com.storeApp.service.implementation;

import com.storeApp.models.Color;
import com.storeApp.models.Phone;
import com.storeApp.repository.ColorRepository;
import com.storeApp.repository.PhoneRepository;
import com.storeApp.service.ColorService;
import com.storeApp.util.exception.OnlineStoreApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;
    private final PhoneRepository phoneRepository;

    @Autowired
    public ColorServiceImpl(ColorRepository colorRepository, PhoneRepository phoneRepository) {
        this.colorRepository = colorRepository;
        this.phoneRepository = phoneRepository;
    }

    @Override
    @Transactional(readOnly = false)
    public Color addNewColor(Color color) {
        return colorRepository.save(color);
    }

    @Override
    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }

    @Override
    public Color getColorById(Long id) {

        Optional<Color> optionalColor = colorRepository.findColorById(id);
        if (optionalColor.isPresent()) {
            return optionalColor.get();
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Color with id - " + id + " not found!");
        }
    }

    @Override
    @Transactional(readOnly = false)
    public Color updateColor(Color editedColor, Long id) {

        Color colorForUpdating = null;
        if (colorRepository.findColorById(id).isPresent()) {
            colorForUpdating = colorRepository.findColorById(id).get();

            colorForUpdating.setColorName(editedColor.getColorName());

            return colorForUpdating;
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Color with id - " + id + " not found!");
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteColor(Long id) {
        Color color = null;
        if (colorRepository.findColorById(id).isPresent()) {

            List<Phone> phonelIst = phoneRepository.findAll();
            color = colorRepository.findColorById(id).get();

            for (Phone phone : phonelIst) {
                phone.getColors().remove(color);
            }

            colorRepository.delete(color);
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Color with id - " + id + " not found!");
        }
    }
}