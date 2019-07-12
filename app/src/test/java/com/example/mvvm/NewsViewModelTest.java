package com.example.mvvm;

import com.example.mvvm.model.NewsResponse;
import com.example.mvvm.networking.BaseApiResponse;
import com.example.mvvm.networking.repository.NewsRepository;
import com.example.mvvm.viewmodels.NewsViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;

/**
 * Created by kostadin.georgiev on 7/12/2019.
 */
@RunWith(JUnit4.class)
public class NewsViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private NewsRepository mNewsRepository;

    @Mock
    Observer<BaseApiResponse<NewsResponse>> observer;

    private NewsViewModel mNewsViewModel;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mNewsViewModel = new NewsViewModel(mNewsRepository);
    }

    @Test
    public void getNewsSuccess() {
        // given
        BaseApiResponse<NewsResponse> baseApiResponse = prepareSuccessResponse();
        MutableLiveData<BaseApiResponse<NewsResponse>> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(baseApiResponse);
        Mockito.when(mNewsRepository.getNews()).thenReturn(mutableLiveData);

        // when
        mNewsViewModel.getNewsLiveData().observeForever(observer);
        mNewsViewModel.getNews();


        // then
        Mockito.verify(observer).onChanged(eq(baseApiResponse));
        assertEquals(mNewsViewModel.getNewsLiveData().getValue().getResponseStatus(), BaseApiResponse.STATUS_OK);
    }

    @Test
    public void getNewsFailure() {
        // given
        BaseApiResponse<NewsResponse> baseApiResponse = prepareFailureResponse();
        MutableLiveData<BaseApiResponse<NewsResponse>> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(baseApiResponse);
        Mockito.when(mNewsRepository.getNews()).thenReturn(mutableLiveData);

        // when
        mNewsViewModel.getNewsLiveData().observeForever(observer);
        mNewsViewModel.getNews();


        // then
        Mockito.verify(observer).onChanged(eq(baseApiResponse));
        assertEquals(mNewsViewModel.getNewsLiveData().getValue().getResponseStatus(), BaseApiResponse.STATUS_FAILED);
    }

    private BaseApiResponse<NewsResponse> prepareSuccessResponse() {
        BaseApiResponse<NewsResponse> baseApiResponse = new BaseApiResponse<>();
        baseApiResponse.setResponseStatus(BaseApiResponse.STATUS_OK);
        NewsResponse newsResponse = new NewsResponse();
        newsResponse.setStatus("test-status");
        newsResponse.setTotalResults(777);
        baseApiResponse.setResponseData(newsResponse);

        return baseApiResponse;
    }

    private BaseApiResponse<NewsResponse> prepareFailureResponse() {
        BaseApiResponse<NewsResponse> baseApiResponse = new BaseApiResponse<>();
        baseApiResponse.setResponseStatus(BaseApiResponse.STATUS_FAILED);
        NewsResponse newsResponse = new NewsResponse();
        newsResponse.setStatus("test-status");
        newsResponse.setTotalResults(777);
        baseApiResponse.setResponseData(newsResponse);

        return baseApiResponse;
    }
}
