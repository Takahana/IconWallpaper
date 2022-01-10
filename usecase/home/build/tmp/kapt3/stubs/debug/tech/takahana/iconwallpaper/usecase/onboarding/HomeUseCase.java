package tech.takahana.iconwallpaper.usecase.onboarding;

import java.lang.System;

@com.futuremind.koru.ToNativeInterface(name = "HomeUseCaseIosProtocol")
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0011\u0010\u0007\u001a\u00020\u0004H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bR\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\t"}, d2 = {"Ltech/takahana/iconwallpaper/usecase/onboarding/HomeUseCase;", "", "onFinishHomeFlow", "Lkotlinx/coroutines/flow/SharedFlow;", "", "getOnFinishHomeFlow", "()Lkotlinx/coroutines/flow/SharedFlow;", "finishHome", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "home_debug"})
public abstract interface HomeUseCase {
    
    /**
     * @return オンボーディングが終了したらUnitを放出するSharedFlow
     */
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.SharedFlow<kotlin.Unit> getOnFinishHomeFlow();
    
    /**
     * オンボーディングを終了する
     */
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object finishHome(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
}