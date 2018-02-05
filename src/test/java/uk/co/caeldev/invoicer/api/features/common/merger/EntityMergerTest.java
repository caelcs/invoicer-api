package uk.co.caeldev.invoicer.api.features.common.merger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EntityMergerTest {

    @Mock
    private Merger<String> merge;

    private EntityMerger entityMerger;

    @Before
    public void testee() {
        final HashMap<Class, Merger> mergers = new HashMap<>();
        mergers.put(String.class, merge);
        entityMerger = new EntityMerger(mergers);
    }

    @Test
    public void shouldGetMergeInstanceForGivenClass() {
        //Given
        final String source = "test";
        final String target = "test2";

        //And
        when(merge.merge(source, target))
                .thenReturn(source);

        //When
        final String merged = entityMerger.merge(source, target, String.class);

        //Then
        assertThat(merged).isEqualTo(source);
    }
}