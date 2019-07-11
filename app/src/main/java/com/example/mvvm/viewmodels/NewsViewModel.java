package com.example.mvvm.viewmodels;

import com.example.mvvm.model.NewsResponse;
import com.example.mvvm.networking.BaseApiResponse;
import com.example.mvvm.networking.repository.NewsRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
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
        if(mNewsMutableLiveData.getValue() == null) {
            mNewsMutableLiveData.addSource(mNewsRepository.getNews(), new Observer<BaseApiResponse<NewsResponse>>() {
                @Override
                public void onChanged(BaseApiResponse<NewsResponse> newsResponse) {
                    mNewsMutableLiveData.setValue(newsResponse);
                }
            });
        }
    }

    public LiveData<BaseApiResponse<NewsResponse>> getNewsLiveData() {
        return mNewsMutableLiveData;
    }
}
