package com.example.mvvm.viewmodels;

import com.example.mvvm.model.NewsResponse;
import com.example.mvvm.networking.BaseApiResponse;
import com.example.mvvm.networking.repository.NewsRepository;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by kostadin.georgiev on 7/10/2019.
 */
public class NewsViewModel extends ViewModel {
    private final MediatorLiveData<BaseApiResponse<NewsResponse>> mNewsMutableLiveData;
    private final NewsRepository mNewsRepository;

    public NewsViewModel() {
        mNewsRepository = NewsRepository.getInstance();
        mNewsMutableLiveData = new MediatorLiveData<>();
    }

    public void getNews() {
        if (mNewsMutableLiveData.getValue() == null) {
            mNewsMutableLiveData.addSource(mNewsRepository.getNews(),
                    newsResponse -> mNewsMutableLiveData.setValue(newsResponse));
        }
    }

    public MutableLiveData<BaseApiResponse<NewsResponse>> getNewsMutableLiveData() {
        return mNewsMutableLiveData;
    }
}
