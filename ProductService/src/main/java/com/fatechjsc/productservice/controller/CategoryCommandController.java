package com.fatechjsc.productservice.controller;

import com.fatechjsc.productservice.command.command.category.CreateCategoryCommand;
import com.fatechjsc.productservice.dto.CategoryDto;
import com.fatechjsc.productservice.exceptions.InvalidParamException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("${api.prefix}/category")
@RequiredArgsConstructor
public class CategoryCommandController {

    private final CommandGateway commandGateway;

    @PostMapping("add")
    public String add(
            @Valid @RequestBody CategoryDto model,
            BindingResult result) throws Exception{
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            throw new InvalidParamException(errors.toString());
        }
        CreateCategoryCommand command = CreateCategoryCommand.builder()
                .id(UUID.randomUUID().toString())
                .name(model.getName())
                .description(model.getDescription())
                .build();
        commandGateway.sendAndWait(command);
        return "create category success";
    }
}
