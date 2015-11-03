package com.bjss.william.employees.controller;

import com.bjss.william.employees.EmployeesApplication;
import com.bjss.william.employees.model.Title;
import com.bjss.william.employees.model.hateoas.TitleResource;
import com.bjss.william.employees.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
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
    public ResponseEntity<List<TitleResource>> getTitlesByEmployeeId(@PathVariable(value = "id") String id) {
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


    @RequestMapping(value = "/{id}/{title}/{date}")
    @ResponseBody
    public ResponseEntity<TitleResource> getTitle(
            @PathVariable(value = "id") String id,
            @PathVariable(value = "title") String titleLiteral,
            @PathVariable(value = "date") String date
    ) {
        try {
            int employeeNumber = Integer.parseInt(id);

            DateFormat df = EmployeesApplication.getDateFormat();
            Date fromDate = df.parse(date);

            Title title = titleService.getTitle(employeeNumber, titleLiteral, fromDate);
            TitleResource titleResource = new TitleResource(title);

            return new ResponseEntity<>(titleResource, HttpStatus.OK);

        } catch (NumberFormatException | ParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
