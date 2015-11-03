package com.bjss.william.employees.controller;

import com.bjss.william.employees.model.Title;
import com.bjss.william.employees.model.hateoas.TitleResource;
import com.bjss.william.employees.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by William Heng(dev) on 28/10/15.
 */

@RestController
@RequestMapping("/titles")
public class TitlesController {

    @Autowired
    private TitleService titleService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<TitleResource>> getTitlesById(@PathVariable(value = "id") String id) {
        try {
            int employeeNumber = Integer.parseInt(id);
            List<Title> titles = titleService.getTitlesByEmployeeNumber(employeeNumber);
            List<TitleResource> titleResources = titles
                    .stream()
                    .map(TitleResource::new)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(titleResources, HttpStatus.OK);

        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
