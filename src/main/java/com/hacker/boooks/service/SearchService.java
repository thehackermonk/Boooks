package com.hacker.boooks.service;

import com.hacker.boooks.bean.SearchResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @apiNote Service class for search
 */
@Service
public interface SearchService {

    /**
     * @apiNote Search entire application
     * @author [@thehackermonk]
     * @since 1.0
     */
    List<SearchResult> search(String keyword);

}
