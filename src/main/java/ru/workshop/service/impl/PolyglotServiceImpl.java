package ru.workshop.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotException;
import org.graalvm.polyglot.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;
import ru.workshop.service.PolyglotService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PolyglotServiceImpl implements PolyglotService {
    @Override
    @SneakyThrows
    public String executeCode(String code) {
        String answer = "";

        try (Context context = Context.create()) {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(this.getClass().getClassLoader());

            List<Resource> resources = new ArrayList<>();
            resources.addAll(List.of(resolver.getResources("classpath:scripts/js/functions/*.js")));
            resources.addAll(List.of(resolver.getResources("classpath:scripts/js/libraries/**/*.js")));
            for (Resource resource : resources) {
                String source = new String(IOUtils.toByteArray(resource.getInputStream()));
                context.eval("js", source);
            }

            Value val = context.eval("js", code);

            answer = val.toString();
        } catch (PolyglotException polyglotException) {
            log.error("Произошла ошибка выполения", polyglotException);
            answer = polyglotException.getMessage();
        } catch (Exception e) {
            log.error("Произошла неизвестная ошибка выполения", e);
            answer = "Произошла неизвестная ошибка выполения";
        }

        return answer;
    }
}
