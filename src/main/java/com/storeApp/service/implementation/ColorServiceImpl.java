package com.storeApp.service.implementation;

import com.storeApp.models.Color;
import com.storeApp.repository.ColorRepository;
import com.storeApp.service.ColorService;
import com.storeApp.util.exception.OnlineStoreApiException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ColorServiceImpl implements ColorService {
    private final ColorRepository colorRepository;

    public ColorServiceImpl(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    @Override
    @Transactional(readOnly = false)
    public Color addNewCase(Color color) {
        return colorRepository.save(color);
    }

    @Override
    public List<Color> getAllCases() {
        return colorRepository.findAll();
    }

    @Override
    public Color getCaseById(Long id) {

        Optional<Color> optionalColor = colorRepository.findColorById(id);
        if (optionalColor.isPresent()) {
            return optionalColor.get();
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Color with id - " + id + " not found!");
        }
    }

    @Override
    @Transactional(readOnly = false)
    public Color updateCase(Color editedColor, Long id) {

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
    public void deleteCase(Long id) {
        Optional<Color> optionalColor = colorRepository.findColorById(id);
        if (optionalColor.isPresent()) {
            colorRepository.delete(optionalColor.get());
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Color with id - " + id + " not found!");
        }
    }
}