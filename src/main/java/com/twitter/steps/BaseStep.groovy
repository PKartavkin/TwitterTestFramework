package com.twitter.steps

import org.slf4j.Logger
import org.slf4j.LoggerFactory

abstract class BaseStep {
    protected final Logger LOG = LoggerFactory.getLogger(getClass());
    private static final TWITTER_DOMAIN_DEFAULT = "https://api.twitter.com/"
    protected static final TWITTER_DOMAIN = System.getProperty("domain") ?: TWITTER_DOMAIN_DEFAULT
}
