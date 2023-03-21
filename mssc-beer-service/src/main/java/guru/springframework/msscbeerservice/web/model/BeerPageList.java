package guru.springframework.msscbeerservice.web.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BeerPageList extends PageImpl {
    public BeerPageList(List content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public BeerPageList(List content) {
        super(content);
    }
}
