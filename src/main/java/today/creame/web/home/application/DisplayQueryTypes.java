package today.creame.web.home.application;

import today.creame.web.share.support.ApplicationContextSupporter;

public enum DisplayQueryTypes {

    ALL {
        @Override
        public DisplayQuery getService() {
            return (DisplayQuery) ApplicationContextSupporter.getBean("allInfluenceQuery");
        }
    },
    RECOMMEND {
        @Override
        public DisplayQuery getService() {
            return (DisplayQuery) ApplicationContextSupporter.getBean("influenceQueryByRecommend");
        }
    },
    LATEST {
        @Override
        public DisplayQuery getService() {
            return (DisplayQuery) ApplicationContextSupporter.getBean("influenceQueryByLatest");
        }
    },
    HOT {
        @Override
        public DisplayQuery getService() {
            return (DisplayQuery) ApplicationContextSupporter.getBean("hotInfluenceQuery");
        }
    },
    CALLING {
        @Override
        public DisplayQuery getService() {
            return (DisplayQuery) ApplicationContextSupporter.getBean("influenceQueryByCalling");
        }
    },
    CATEGORY {
        @Override
        public DisplayQuery getService() {
            return (DisplayQuery) ApplicationContextSupporter.getBean("influenceQueryByCategory");
        }
    },
    KEYWORD {
        @Override
        public DisplayQuery getService() {
            return (DisplayQuery) ApplicationContextSupporter.getBean("influenceQueryByKeyword");
        }
    },

    ;

    public abstract DisplayQuery getService();
}
