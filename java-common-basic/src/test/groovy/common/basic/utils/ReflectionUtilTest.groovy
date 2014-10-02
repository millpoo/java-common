package common.basic.utils

import spock.lang.Specification

import java.lang.reflect.Field

class ReflectionUtilTest extends Specification {

    static class Test1 {

        @AnnotationForTest
        public String s;

        @AnnotationForTest
        public int i;

        public long l;

        Test1() {
        }

        Test1(String s, int i, long l) {
            this.s = s
            this.i = i
            this.l = l
        }

        boolean equals(o) {
            if (this.is(o)) return true
            if (!(o instanceof Test1)) return false

            Test1 test1 = (Test1) o

            if (i != test1.i) return false
            if (l != test1.l) return false
            if (s != test1.s) return false

            return true
        }

        int hashCode() {
            int result
            result = (s != null ? s.hashCode() : 0)
            result = 31 * result + i
            result = 31 * result + (int) (l ^ (l >>> 32))
            return result
        }
    }

    def "GetAnnotatedField"() {
        List<Field> list = ReflectionUtil.getAnnotatedField(Test1.class, AnnotationForTest.class)

        expect:
        list.size() == 2
        list[0].name == "s"
        list[1].name == "i"
    }

    def "GetAnnotatedFieldFirst"() {
        def field = ReflectionUtil.getAnnotatedFieldFirst(Test1.class, AnnotationForTest.class)

        expect:
        field.name == "s"
    }

    def "GetAnnotatedFieldNameFirst"() {
        def name = ReflectionUtil.getAnnotatedFieldNameFirst(Test1.class, AnnotationForTest.class)
        expect:
        name == "s"
    }

    def "GetAnnotatedFieldValueFirst"() {
        expect:
        "s" == ReflectionUtil.getAnnotatedFieldValueFirst(new Test1("s", 1, 10), AnnotationForTest.class)
    }

    def "GetMapField"() {
        Map<String, Field> map = ReflectionUtil.getMapField(Test1.class);

        expect:
        map.keySet().containsAll(["s", "i", "l"])
    }

    def "GetValue"() {

    }

    def "FromListMap"() {
        expect:
        [new Test1("s1", 11, 1000l), new Test1("s2", 12, 2000l)] == ReflectionUtil.fromListMap(Test1.class, [["s":"s1", "i":11, "l":1000l], ["s":"s2", "i":12, "l":2000l]])
    }

    def "FromListMap1"() {

    }

    def "FromMap"() {
        expect:
        new Test1("s", 10, 1000l) == ReflectionUtil.fromMap(Test1.class, ["s":"s", "i":10, "l":1000l])
    }

    def "FromMap1"() {

    }

    def "ToMap"() {

    }

    def "GetListFieldDeclaredRecursive"() {

    }

    def "GetMapFieldDeclaredRecursive"() {

    }

    def "GetFieldDeclaredRecursive"() {

    }
}
