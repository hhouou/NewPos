package com.achpay.wallet.util.Cookie;

/**
 * Created by hy on 2018/4/11.
 */


import com.achpay.wallet.util.Cookie.cache.CookieCache;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.achpay.wallet.util.Cookie.cache.IdentifiableCookie;
import okhttp3.Cookie;

public class SetCookieCache implements CookieCache {

    private Set<IdentifiableCookie> cookies;

    public SetCookieCache() {
        cookies = new HashSet<>();
    }

    @Override
    public void addAll(Collection<Cookie> newCookies) {
        for (IdentifiableCookie cookie : IdentifiableCookie.decorateAll(newCookies)) {
            this.cookies.remove(cookie);
            this.cookies.add(cookie);
        }
    }

    @Override
    public void clear() {
        cookies.clear();
    }

    @Override
    public Iterator<Cookie> iterator() {
        return new SetCookieCache.SetCookieCacheIterator();
    }

    private class SetCookieCacheIterator implements Iterator<Cookie> {

        private Iterator<IdentifiableCookie> iterator;

        public SetCookieCacheIterator() {
            iterator = cookies.iterator();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Cookie next() {
            return iterator.next().getCookie();
        }

        @Override
        public void remove() {
            iterator.remove();
        }
    }
}
