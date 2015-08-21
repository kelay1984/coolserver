package com.topda.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MonitorTemperatureExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public MonitorTemperatureExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimitStart(Integer limitStart) {
        this.limitStart=limitStart;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(Integer limitEnd) {
        this.limitEnd=limitEnd;
    }

    public Integer getLimitEnd() {
        return limitEnd;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andKeyidIsNull() {
            addCriterion("Keyid is null");
            return (Criteria) this;
        }

        public Criteria andKeyidIsNotNull() {
            addCriterion("Keyid is not null");
            return (Criteria) this;
        }

        public Criteria andKeyidEqualTo(Integer value) {
            addCriterion("Keyid =", value, "keyid");
            return (Criteria) this;
        }

        public Criteria andKeyidNotEqualTo(Integer value) {
            addCriterion("Keyid <>", value, "keyid");
            return (Criteria) this;
        }

        public Criteria andKeyidGreaterThan(Integer value) {
            addCriterion("Keyid >", value, "keyid");
            return (Criteria) this;
        }

        public Criteria andKeyidGreaterThanOrEqualTo(Integer value) {
            addCriterion("Keyid >=", value, "keyid");
            return (Criteria) this;
        }

        public Criteria andKeyidLessThan(Integer value) {
            addCriterion("Keyid <", value, "keyid");
            return (Criteria) this;
        }

        public Criteria andKeyidLessThanOrEqualTo(Integer value) {
            addCriterion("Keyid <=", value, "keyid");
            return (Criteria) this;
        }

        public Criteria andKeyidIn(List<Integer> values) {
            addCriterion("Keyid in", values, "keyid");
            return (Criteria) this;
        }

        public Criteria andKeyidNotIn(List<Integer> values) {
            addCriterion("Keyid not in", values, "keyid");
            return (Criteria) this;
        }

        public Criteria andKeyidBetween(Integer value1, Integer value2) {
            addCriterion("Keyid between", value1, value2, "keyid");
            return (Criteria) this;
        }

        public Criteria andKeyidNotBetween(Integer value1, Integer value2) {
            addCriterion("Keyid not between", value1, value2, "keyid");
            return (Criteria) this;
        }

        public Criteria andDatehappenIsNull() {
            addCriterion("datehappen is null");
            return (Criteria) this;
        }

        public Criteria andDatehappenIsNotNull() {
            addCriterion("datehappen is not null");
            return (Criteria) this;
        }

        public Criteria andDatehappenEqualTo(Date value) {
            addCriterion("datehappen =", value, "datehappen");
            return (Criteria) this;
        }

        public Criteria andDatehappenNotEqualTo(Date value) {
            addCriterion("datehappen <>", value, "datehappen");
            return (Criteria) this;
        }

        public Criteria andDatehappenGreaterThan(Date value) {
            addCriterion("datehappen >", value, "datehappen");
            return (Criteria) this;
        }

        public Criteria andDatehappenGreaterThanOrEqualTo(Date value) {
            addCriterion("datehappen >=", value, "datehappen");
            return (Criteria) this;
        }

        public Criteria andDatehappenLessThan(Date value) {
            addCriterion("datehappen <", value, "datehappen");
            return (Criteria) this;
        }

        public Criteria andDatehappenLessThanOrEqualTo(Date value) {
            addCriterion("datehappen <=", value, "datehappen");
            return (Criteria) this;
        }

        public Criteria andDatehappenIn(List<Date> values) {
            addCriterion("datehappen in", values, "datehappen");
            return (Criteria) this;
        }

        public Criteria andDatehappenNotIn(List<Date> values) {
            addCriterion("datehappen not in", values, "datehappen");
            return (Criteria) this;
        }

        public Criteria andDatehappenBetween(Date value1, Date value2) {
            addCriterion("datehappen between", value1, value2, "datehappen");
            return (Criteria) this;
        }

        public Criteria andDatehappenNotBetween(Date value1, Date value2) {
            addCriterion("datehappen not between", value1, value2, "datehappen");
            return (Criteria) this;
        }

        public Criteria andTemperatureIsNull() {
            addCriterion("temperature is null");
            return (Criteria) this;
        }

        public Criteria andTemperatureIsNotNull() {
            addCriterion("temperature is not null");
            return (Criteria) this;
        }

        public Criteria andTemperatureEqualTo(Float value) {
            addCriterion("temperature =", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureNotEqualTo(Float value) {
            addCriterion("temperature <>", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureGreaterThan(Float value) {
            addCriterion("temperature >", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureGreaterThanOrEqualTo(Float value) {
            addCriterion("temperature >=", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureLessThan(Float value) {
            addCriterion("temperature <", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureLessThanOrEqualTo(Float value) {
            addCriterion("temperature <=", value, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureIn(List<Float> values) {
            addCriterion("temperature in", values, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureNotIn(List<Float> values) {
            addCriterion("temperature not in", values, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureBetween(Float value1, Float value2) {
            addCriterion("temperature between", value1, value2, "temperature");
            return (Criteria) this;
        }

        public Criteria andTemperatureNotBetween(Float value1, Float value2) {
            addCriterion("temperature not between", value1, value2, "temperature");
            return (Criteria) this;
        }

        public Criteria andServerdatetimeIsNull() {
            addCriterion("serverdatetime is null");
            return (Criteria) this;
        }

        public Criteria andServerdatetimeIsNotNull() {
            addCriterion("serverdatetime is not null");
            return (Criteria) this;
        }

        public Criteria andServerdatetimeEqualTo(Date value) {
            addCriterion("serverdatetime =", value, "serverdatetime");
            return (Criteria) this;
        }

        public Criteria andServerdatetimeNotEqualTo(Date value) {
            addCriterion("serverdatetime <>", value, "serverdatetime");
            return (Criteria) this;
        }

        public Criteria andServerdatetimeGreaterThan(Date value) {
            addCriterion("serverdatetime >", value, "serverdatetime");
            return (Criteria) this;
        }

        public Criteria andServerdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("serverdatetime >=", value, "serverdatetime");
            return (Criteria) this;
        }

        public Criteria andServerdatetimeLessThan(Date value) {
            addCriterion("serverdatetime <", value, "serverdatetime");
            return (Criteria) this;
        }

        public Criteria andServerdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("serverdatetime <=", value, "serverdatetime");
            return (Criteria) this;
        }

        public Criteria andServerdatetimeIn(List<Date> values) {
            addCriterion("serverdatetime in", values, "serverdatetime");
            return (Criteria) this;
        }

        public Criteria andServerdatetimeNotIn(List<Date> values) {
            addCriterion("serverdatetime not in", values, "serverdatetime");
            return (Criteria) this;
        }

        public Criteria andServerdatetimeBetween(Date value1, Date value2) {
            addCriterion("serverdatetime between", value1, value2, "serverdatetime");
            return (Criteria) this;
        }

        public Criteria andServerdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("serverdatetime not between", value1, value2, "serverdatetime");
            return (Criteria) this;
        }

        public Criteria andBoxsnIsNull() {
            addCriterion("boxsn is null");
            return (Criteria) this;
        }

        public Criteria andBoxsnIsNotNull() {
            addCriterion("boxsn is not null");
            return (Criteria) this;
        }

        public Criteria andBoxsnEqualTo(String value) {
            addCriterion("boxsn =", value, "boxsn");
            return (Criteria) this;
        }

        public Criteria andBoxsnNotEqualTo(String value) {
            addCriterion("boxsn <>", value, "boxsn");
            return (Criteria) this;
        }

        public Criteria andBoxsnGreaterThan(String value) {
            addCriterion("boxsn >", value, "boxsn");
            return (Criteria) this;
        }

        public Criteria andBoxsnGreaterThanOrEqualTo(String value) {
            addCriterion("boxsn >=", value, "boxsn");
            return (Criteria) this;
        }

        public Criteria andBoxsnLessThan(String value) {
            addCriterion("boxsn <", value, "boxsn");
            return (Criteria) this;
        }

        public Criteria andBoxsnLessThanOrEqualTo(String value) {
            addCriterion("boxsn <=", value, "boxsn");
            return (Criteria) this;
        }

        public Criteria andBoxsnLike(String value) {
            addCriterion("boxsn like", value, "boxsn");
            return (Criteria) this;
        }

        public Criteria andBoxsnNotLike(String value) {
            addCriterion("boxsn not like", value, "boxsn");
            return (Criteria) this;
        }

        public Criteria andBoxsnIn(List<String> values) {
            addCriterion("boxsn in", values, "boxsn");
            return (Criteria) this;
        }

        public Criteria andBoxsnNotIn(List<String> values) {
            addCriterion("boxsn not in", values, "boxsn");
            return (Criteria) this;
        }

        public Criteria andBoxsnBetween(String value1, String value2) {
            addCriterion("boxsn between", value1, value2, "boxsn");
            return (Criteria) this;
        }

        public Criteria andBoxsnNotBetween(String value1, String value2) {
            addCriterion("boxsn not between", value1, value2, "boxsn");
            return (Criteria) this;
        }

        public Criteria andPowerIsNull() {
            addCriterion("power is null");
            return (Criteria) this;
        }

        public Criteria andPowerIsNotNull() {
            addCriterion("power is not null");
            return (Criteria) this;
        }

        public Criteria andPowerEqualTo(Integer value) {
            addCriterion("power =", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerNotEqualTo(Integer value) {
            addCriterion("power <>", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerGreaterThan(Integer value) {
            addCriterion("power >", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerGreaterThanOrEqualTo(Integer value) {
            addCriterion("power >=", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerLessThan(Integer value) {
            addCriterion("power <", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerLessThanOrEqualTo(Integer value) {
            addCriterion("power <=", value, "power");
            return (Criteria) this;
        }

        public Criteria andPowerIn(List<Integer> values) {
            addCriterion("power in", values, "power");
            return (Criteria) this;
        }

        public Criteria andPowerNotIn(List<Integer> values) {
            addCriterion("power not in", values, "power");
            return (Criteria) this;
        }

        public Criteria andPowerBetween(Integer value1, Integer value2) {
            addCriterion("power between", value1, value2, "power");
            return (Criteria) this;
        }

        public Criteria andPowerNotBetween(Integer value1, Integer value2) {
            addCriterion("power not between", value1, value2, "power");
            return (Criteria) this;
        }

        public Criteria andDoorstateIsNull() {
            addCriterion("doorstate is null");
            return (Criteria) this;
        }

        public Criteria andDoorstateIsNotNull() {
            addCriterion("doorstate is not null");
            return (Criteria) this;
        }

        public Criteria andDoorstateEqualTo(Integer value) {
            addCriterion("doorstate =", value, "doorstate");
            return (Criteria) this;
        }

        public Criteria andDoorstateNotEqualTo(Integer value) {
            addCriterion("doorstate <>", value, "doorstate");
            return (Criteria) this;
        }

        public Criteria andDoorstateGreaterThan(Integer value) {
            addCriterion("doorstate >", value, "doorstate");
            return (Criteria) this;
        }

        public Criteria andDoorstateGreaterThanOrEqualTo(Integer value) {
            addCriterion("doorstate >=", value, "doorstate");
            return (Criteria) this;
        }

        public Criteria andDoorstateLessThan(Integer value) {
            addCriterion("doorstate <", value, "doorstate");
            return (Criteria) this;
        }

        public Criteria andDoorstateLessThanOrEqualTo(Integer value) {
            addCriterion("doorstate <=", value, "doorstate");
            return (Criteria) this;
        }

        public Criteria andDoorstateIn(List<Integer> values) {
            addCriterion("doorstate in", values, "doorstate");
            return (Criteria) this;
        }

        public Criteria andDoorstateNotIn(List<Integer> values) {
            addCriterion("doorstate not in", values, "doorstate");
            return (Criteria) this;
        }

        public Criteria andDoorstateBetween(Integer value1, Integer value2) {
            addCriterion("doorstate between", value1, value2, "doorstate");
            return (Criteria) this;
        }

        public Criteria andDoorstateNotBetween(Integer value1, Integer value2) {
            addCriterion("doorstate not between", value1, value2, "doorstate");
            return (Criteria) this;
        }

        public Criteria andSupplyidIsNull() {
            addCriterion("supplyid is null");
            return (Criteria) this;
        }

        public Criteria andSupplyidIsNotNull() {
            addCriterion("supplyid is not null");
            return (Criteria) this;
        }

        public Criteria andSupplyidEqualTo(Integer value) {
            addCriterion("supplyid =", value, "supplyid");
            return (Criteria) this;
        }

        public Criteria andSupplyidNotEqualTo(Integer value) {
            addCriterion("supplyid <>", value, "supplyid");
            return (Criteria) this;
        }

        public Criteria andSupplyidGreaterThan(Integer value) {
            addCriterion("supplyid >", value, "supplyid");
            return (Criteria) this;
        }

        public Criteria andSupplyidGreaterThanOrEqualTo(Integer value) {
            addCriterion("supplyid >=", value, "supplyid");
            return (Criteria) this;
        }

        public Criteria andSupplyidLessThan(Integer value) {
            addCriterion("supplyid <", value, "supplyid");
            return (Criteria) this;
        }

        public Criteria andSupplyidLessThanOrEqualTo(Integer value) {
            addCriterion("supplyid <=", value, "supplyid");
            return (Criteria) this;
        }

        public Criteria andSupplyidIn(List<Integer> values) {
            addCriterion("supplyid in", values, "supplyid");
            return (Criteria) this;
        }

        public Criteria andSupplyidNotIn(List<Integer> values) {
            addCriterion("supplyid not in", values, "supplyid");
            return (Criteria) this;
        }

        public Criteria andSupplyidBetween(Integer value1, Integer value2) {
            addCriterion("supplyid between", value1, value2, "supplyid");
            return (Criteria) this;
        }

        public Criteria andSupplyidNotBetween(Integer value1, Integer value2) {
            addCriterion("supplyid not between", value1, value2, "supplyid");
            return (Criteria) this;
        }

        public Criteria andSwarningIsNull() {
            addCriterion("swarning is null");
            return (Criteria) this;
        }

        public Criteria andSwarningIsNotNull() {
            addCriterion("swarning is not null");
            return (Criteria) this;
        }

        public Criteria andSwarningEqualTo(Integer value) {
            addCriterion("swarning =", value, "swarning");
            return (Criteria) this;
        }

        public Criteria andSwarningNotEqualTo(Integer value) {
            addCriterion("swarning <>", value, "swarning");
            return (Criteria) this;
        }

        public Criteria andSwarningGreaterThan(Integer value) {
            addCriterion("swarning >", value, "swarning");
            return (Criteria) this;
        }

        public Criteria andSwarningGreaterThanOrEqualTo(Integer value) {
            addCriterion("swarning >=", value, "swarning");
            return (Criteria) this;
        }

        public Criteria andSwarningLessThan(Integer value) {
            addCriterion("swarning <", value, "swarning");
            return (Criteria) this;
        }

        public Criteria andSwarningLessThanOrEqualTo(Integer value) {
            addCriterion("swarning <=", value, "swarning");
            return (Criteria) this;
        }

        public Criteria andSwarningIn(List<Integer> values) {
            addCriterion("swarning in", values, "swarning");
            return (Criteria) this;
        }

        public Criteria andSwarningNotIn(List<Integer> values) {
            addCriterion("swarning not in", values, "swarning");
            return (Criteria) this;
        }

        public Criteria andSwarningBetween(Integer value1, Integer value2) {
            addCriterion("swarning between", value1, value2, "swarning");
            return (Criteria) this;
        }

        public Criteria andSwarningNotBetween(Integer value1, Integer value2) {
            addCriterion("swarning not between", value1, value2, "swarning");
            return (Criteria) this;
        }

        public Criteria andSrestrictionIsNull() {
            addCriterion("srestriction is null");
            return (Criteria) this;
        }

        public Criteria andSrestrictionIsNotNull() {
            addCriterion("srestriction is not null");
            return (Criteria) this;
        }

        public Criteria andSrestrictionEqualTo(Integer value) {
            addCriterion("srestriction =", value, "srestriction");
            return (Criteria) this;
        }

        public Criteria andSrestrictionNotEqualTo(Integer value) {
            addCriterion("srestriction <>", value, "srestriction");
            return (Criteria) this;
        }

        public Criteria andSrestrictionGreaterThan(Integer value) {
            addCriterion("srestriction >", value, "srestriction");
            return (Criteria) this;
        }

        public Criteria andSrestrictionGreaterThanOrEqualTo(Integer value) {
            addCriterion("srestriction >=", value, "srestriction");
            return (Criteria) this;
        }

        public Criteria andSrestrictionLessThan(Integer value) {
            addCriterion("srestriction <", value, "srestriction");
            return (Criteria) this;
        }

        public Criteria andSrestrictionLessThanOrEqualTo(Integer value) {
            addCriterion("srestriction <=", value, "srestriction");
            return (Criteria) this;
        }

        public Criteria andSrestrictionIn(List<Integer> values) {
            addCriterion("srestriction in", values, "srestriction");
            return (Criteria) this;
        }

        public Criteria andSrestrictionNotIn(List<Integer> values) {
            addCriterion("srestriction not in", values, "srestriction");
            return (Criteria) this;
        }

        public Criteria andSrestrictionBetween(Integer value1, Integer value2) {
            addCriterion("srestriction between", value1, value2, "srestriction");
            return (Criteria) this;
        }

        public Criteria andSrestrictionNotBetween(Integer value1, Integer value2) {
            addCriterion("srestriction not between", value1, value2, "srestriction");
            return (Criteria) this;
        }

        public Criteria andLrestrictionIsNull() {
            addCriterion("lrestriction is null");
            return (Criteria) this;
        }

        public Criteria andLrestrictionIsNotNull() {
            addCriterion("lrestriction is not null");
            return (Criteria) this;
        }

        public Criteria andLrestrictionEqualTo(Integer value) {
            addCriterion("lrestriction =", value, "lrestriction");
            return (Criteria) this;
        }

        public Criteria andLrestrictionNotEqualTo(Integer value) {
            addCriterion("lrestriction <>", value, "lrestriction");
            return (Criteria) this;
        }

        public Criteria andLrestrictionGreaterThan(Integer value) {
            addCriterion("lrestriction >", value, "lrestriction");
            return (Criteria) this;
        }

        public Criteria andLrestrictionGreaterThanOrEqualTo(Integer value) {
            addCriterion("lrestriction >=", value, "lrestriction");
            return (Criteria) this;
        }

        public Criteria andLrestrictionLessThan(Integer value) {
            addCriterion("lrestriction <", value, "lrestriction");
            return (Criteria) this;
        }

        public Criteria andLrestrictionLessThanOrEqualTo(Integer value) {
            addCriterion("lrestriction <=", value, "lrestriction");
            return (Criteria) this;
        }

        public Criteria andLrestrictionIn(List<Integer> values) {
            addCriterion("lrestriction in", values, "lrestriction");
            return (Criteria) this;
        }

        public Criteria andLrestrictionNotIn(List<Integer> values) {
            addCriterion("lrestriction not in", values, "lrestriction");
            return (Criteria) this;
        }

        public Criteria andLrestrictionBetween(Integer value1, Integer value2) {
            addCriterion("lrestriction between", value1, value2, "lrestriction");
            return (Criteria) this;
        }

        public Criteria andLrestrictionNotBetween(Integer value1, Integer value2) {
            addCriterion("lrestriction not between", value1, value2, "lrestriction");
            return (Criteria) this;
        }

        public Criteria andLwarningIsNull() {
            addCriterion("lwarning is null");
            return (Criteria) this;
        }

        public Criteria andLwarningIsNotNull() {
            addCriterion("lwarning is not null");
            return (Criteria) this;
        }

        public Criteria andLwarningEqualTo(Integer value) {
            addCriterion("lwarning =", value, "lwarning");
            return (Criteria) this;
        }

        public Criteria andLwarningNotEqualTo(Integer value) {
            addCriterion("lwarning <>", value, "lwarning");
            return (Criteria) this;
        }

        public Criteria andLwarningGreaterThan(Integer value) {
            addCriterion("lwarning >", value, "lwarning");
            return (Criteria) this;
        }

        public Criteria andLwarningGreaterThanOrEqualTo(Integer value) {
            addCriterion("lwarning >=", value, "lwarning");
            return (Criteria) this;
        }

        public Criteria andLwarningLessThan(Integer value) {
            addCriterion("lwarning <", value, "lwarning");
            return (Criteria) this;
        }

        public Criteria andLwarningLessThanOrEqualTo(Integer value) {
            addCriterion("lwarning <=", value, "lwarning");
            return (Criteria) this;
        }

        public Criteria andLwarningIn(List<Integer> values) {
            addCriterion("lwarning in", values, "lwarning");
            return (Criteria) this;
        }

        public Criteria andLwarningNotIn(List<Integer> values) {
            addCriterion("lwarning not in", values, "lwarning");
            return (Criteria) this;
        }

        public Criteria andLwarningBetween(Integer value1, Integer value2) {
            addCriterion("lwarning between", value1, value2, "lwarning");
            return (Criteria) this;
        }

        public Criteria andLwarningNotBetween(Integer value1, Integer value2) {
            addCriterion("lwarning not between", value1, value2, "lwarning");
            return (Criteria) this;
        }

        public Criteria andReserveIsNull() {
            addCriterion("reserve is null");
            return (Criteria) this;
        }

        public Criteria andReserveIsNotNull() {
            addCriterion("reserve is not null");
            return (Criteria) this;
        }

        public Criteria andReserveEqualTo(Integer value) {
            addCriterion("reserve =", value, "reserve");
            return (Criteria) this;
        }

        public Criteria andReserveNotEqualTo(Integer value) {
            addCriterion("reserve <>", value, "reserve");
            return (Criteria) this;
        }

        public Criteria andReserveGreaterThan(Integer value) {
            addCriterion("reserve >", value, "reserve");
            return (Criteria) this;
        }

        public Criteria andReserveGreaterThanOrEqualTo(Integer value) {
            addCriterion("reserve >=", value, "reserve");
            return (Criteria) this;
        }

        public Criteria andReserveLessThan(Integer value) {
            addCriterion("reserve <", value, "reserve");
            return (Criteria) this;
        }

        public Criteria andReserveLessThanOrEqualTo(Integer value) {
            addCriterion("reserve <=", value, "reserve");
            return (Criteria) this;
        }

        public Criteria andReserveIn(List<Integer> values) {
            addCriterion("reserve in", values, "reserve");
            return (Criteria) this;
        }

        public Criteria andReserveNotIn(List<Integer> values) {
            addCriterion("reserve not in", values, "reserve");
            return (Criteria) this;
        }

        public Criteria andReserveBetween(Integer value1, Integer value2) {
            addCriterion("reserve between", value1, value2, "reserve");
            return (Criteria) this;
        }

        public Criteria andReserveNotBetween(Integer value1, Integer value2) {
            addCriterion("reserve not between", value1, value2, "reserve");
            return (Criteria) this;
        }

        public Criteria andOuttemperatureIsNull() {
            addCriterion("outtemperature is null");
            return (Criteria) this;
        }

        public Criteria andOuttemperatureIsNotNull() {
            addCriterion("outtemperature is not null");
            return (Criteria) this;
        }

        public Criteria andOuttemperatureEqualTo(Float value) {
            addCriterion("outtemperature =", value, "outtemperature");
            return (Criteria) this;
        }

        public Criteria andOuttemperatureNotEqualTo(Float value) {
            addCriterion("outtemperature <>", value, "outtemperature");
            return (Criteria) this;
        }

        public Criteria andOuttemperatureGreaterThan(Float value) {
            addCriterion("outtemperature >", value, "outtemperature");
            return (Criteria) this;
        }

        public Criteria andOuttemperatureGreaterThanOrEqualTo(Float value) {
            addCriterion("outtemperature >=", value, "outtemperature");
            return (Criteria) this;
        }

        public Criteria andOuttemperatureLessThan(Float value) {
            addCriterion("outtemperature <", value, "outtemperature");
            return (Criteria) this;
        }

        public Criteria andOuttemperatureLessThanOrEqualTo(Float value) {
            addCriterion("outtemperature <=", value, "outtemperature");
            return (Criteria) this;
        }

        public Criteria andOuttemperatureIn(List<Float> values) {
            addCriterion("outtemperature in", values, "outtemperature");
            return (Criteria) this;
        }

        public Criteria andOuttemperatureNotIn(List<Float> values) {
            addCriterion("outtemperature not in", values, "outtemperature");
            return (Criteria) this;
        }

        public Criteria andOuttemperatureBetween(Float value1, Float value2) {
            addCriterion("outtemperature between", value1, value2, "outtemperature");
            return (Criteria) this;
        }

        public Criteria andOuttemperatureNotBetween(Float value1, Float value2) {
            addCriterion("outtemperature not between", value1, value2, "outtemperature");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}