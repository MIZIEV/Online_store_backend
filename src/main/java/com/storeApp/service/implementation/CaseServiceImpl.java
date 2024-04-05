package com.storeApp.service.implementation;

import com.storeApp.models.Case;
import com.storeApp.models.Color;
import com.storeApp.repository.CaseRepository;
import com.storeApp.repository.ColorRepository;
import com.storeApp.service.CaseService;
import com.storeApp.util.exception.OnlineStoreApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CaseServiceImpl implements CaseService {
    private final CaseRepository caseRepository;
    private final ColorRepository colorRepository;

    @Autowired
    public CaseServiceImpl(CaseRepository caseRepository, ColorRepository colorRepository) {
        this.caseRepository = caseRepository;
        this.colorRepository = colorRepository;
    }

    @Override
    @Transactional(readOnly = false)
    public Case addNewCase(Case newCase) {
        return caseRepository.save(newCase);
    }

    @Override
    public List<Case> getAllCases() {
        return caseRepository.findAll();
    }

    @Override
    public Case getCaseById(Long id) {
        Optional<Case> optionalCase = caseRepository.findCaseById(id);

        if (optionalCase.isPresent()) {
            return optionalCase.get();
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Case with id - " + id + " not found!");
        }
    }

    @Override
    @Transactional(readOnly = false)
    public Case updateCase(Case editedCase, Long id) {

        Case updatedCase = null;

        if (caseRepository.findCaseById(id).isPresent()) {
            updatedCase = caseRepository.findCaseById(id).get();

            updatedCase.setId(id);
            updatedCase.setModel(editedCase.getModel());
            updatedCase.setBrand(editedCase.getBrand());
            updatedCase.setDescription(editedCase.getDescription());
            updatedCase.setRating(editedCase.getRating());
            updatedCase.setPrice(editedCase.getPrice());
            updatedCase.setVoteCount(editedCase.getVoteCount());
            updatedCase.setMaterial(editedCase.getMaterial());
            updatedCase.setProducingCountry(editedCase.getProducingCountry());

            caseRepository.save(updatedCase);
            return updatedCase;
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Case with id - " + id + " not found!");
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteCase(Long id) {
        Optional<Case> optionalCase = caseRepository.findCaseById(id);

        if (optionalCase.isPresent()) {
            caseRepository.delete(optionalCase.get());
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Case with id - " + id + " not found!");
        }
    }

    @Transactional(readOnly = false)
    @Override
    public void putTheMark(Long id, Double mark) {
        Case caseEntity = caseRepository.findCaseById(id).get();
        Double currentRating = null;
        Long voteCount = null;

        if (caseEntity.getVoteCount() == null) {
            caseEntity.setRating(0.0);
            caseEntity.setVoteCount(0L);
        }

        currentRating = caseEntity.getRating() * caseEntity.getVoteCount();
        voteCount = caseEntity.getVoteCount() + 1;

        caseEntity.setVoteCount(voteCount);
        caseEntity.setRating((currentRating + mark) / voteCount);

        caseRepository.save(caseEntity);
    }

    @Override
    @Transactional(readOnly = false)
    public String putTheColors(Long id, Set<Long> colorIds) {
        if (caseRepository.findCaseById(id).isPresent()) {

            Case caseEntity = caseRepository.findCaseById(id).get();
            Set<Color> colors = colorRepository.findByColorsIds(colorIds);

            Set<Long> existingColorIds = colors.stream().map(Color::getId).collect(Collectors.toSet());
            Set<Long> nonExistingColorIds = colorIds.stream()
                    .filter(colorId -> !existingColorIds.contains(colorId))
                    .collect(Collectors.toSet());

            if (colors.isEmpty()) {
                throw new OnlineStoreApiException(HttpStatus.NOT_FOUND,
                        "Colors with id - " + nonExistingColorIds + " not found!");
            } else if (nonExistingColorIds.isEmpty()) {
                caseEntity.setColors(colors);
                caseRepository.save(caseEntity);
                return "colors with id" + existingColorIds + " is putted! ";
            } else {
                caseEntity.setColors(colors);
                caseRepository.save(caseEntity);
                return "Color with id" + existingColorIds + " is putted!\n" +
                        "Color with id" + nonExistingColorIds + " not found!";
            }
        } else {
            throw new OnlineStoreApiException(HttpStatus.NOT_FOUND, "Case with id - " + id + " not found!");
        }
    }
}