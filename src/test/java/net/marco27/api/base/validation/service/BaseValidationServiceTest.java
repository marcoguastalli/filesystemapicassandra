package net.marco27.api.base.validation.service;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.marco27.api.base.validation.model.BaseValidationModel;
import net.marco27.api.base.validation.model.BaseValidationResult;

@RunWith(SpringJUnit4ClassRunner.class)
public class BaseValidationServiceTest {

    private BaseValidationService baseValidationService;

    @Before
    public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
        baseValidationService = new BaseValidationServiceImpl();
    }

    @Test
    public void testValidate() {
        final BaseValidationResult result = baseValidationService.validate(new BaseValidationModel(StringUtils.SPACE));
        assertTrue(result.isValid());
    }
}
