package core

import groovy.util.XmlSlurper
import groovy.util.slurpersupport.GPathResult

import spock.lang.Specification

class GPathResultFinderExtensionSpec extends Specification {

    def 'Loofing for a group of nodes within a XML'() {
        given: 'A xml with book references'
            def response = parseBooksXml()
        when: 'Looking for a specific set of books'
            def results =
                response.
                findAllBookWhere { book-> book.author.text() == 'Miguel De Cervantes'}
        then: 'We should be able to find them'
            with(results) {
                size() == 2
                first().title.text() == 'Don Xijote'
                last().title.text() == 'Rinconete and Cortadillo'
            }
    }

    GPathResult parseBooksXml() {
        return new XmlSlurper().parse(new File("src/test/resources/books.xml"))
    }

}
