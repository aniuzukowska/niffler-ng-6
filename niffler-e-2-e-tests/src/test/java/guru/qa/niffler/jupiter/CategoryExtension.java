package guru.qa.niffler.jupiter;

import guru.qa.niffler.api.SpendApiClient;
import guru.qa.niffler.generators.RandomData;
import guru.qa.niffler.model.CategoryJson;
import guru.qa.niffler.model.SpendJson;
import org.apache.kafka.common.protocol.types.Field;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.support.AnnotationSupport;

public class CategoryExtension implements BeforeEachCallback, AfterTestExecutionCallback, ParameterResolver {
    public static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(SpendingExtension.class);

    private final SpendApiClient spendApiClient = new SpendApiClient();

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        //добавить пользователю категорию перед тестом
        //используйте рандомное имя для категории внутри CategoryExtension
        //для архивных делать два запроса - сначала создать, потом сделать архив


        AnnotationSupport.findAnnotation(context.getRequiredTestMethod(), Category.class)
                .ifPresent(anno -> {
                    CategoryJson category = new CategoryJson(
                            null,
                            RandomData.getCategory(),
                            anno.username(),
                            false
                    );
                    CategoryJson created = spendApiClient.createCategory(category);

                    if (anno.archived()) {
                        CategoryJson archivedCategory = new CategoryJson(
                                created.id(),
                                created.name(),
                                created.username(),
                                true
                        );
                        created = spendApiClient.updateCategory(archivedCategory);
                    }

                    context.getStore(NAMESPACE).put(
                            context.getUniqueId(),
                            created
                    );
                });
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().isAssignableFrom(CategoryJson.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return extensionContext.getStore(NAMESPACE).get(extensionContext.getUniqueId(), CategoryJson.class);
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        CategoryJson category = context
                .getStore(CategoryExtension.NAMESPACE)
                .get(context.getUniqueId(), CategoryJson.class);

        if (!category.archived()) {
            CategoryJson archivedCategory = new CategoryJson(
                    category.id(),
                    category.name(),
                    category.username(),
                    true
            );
            spendApiClient.updateCategory(archivedCategory);
        }
    }
}
