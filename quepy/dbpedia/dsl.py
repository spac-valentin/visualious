# coding: utf-8

# Copyright (c) 2012, Machinalis S.R.L.
# This file is part of quepy and is distributed under the Modified BSD License.
# You should have received a copy of license in the LICENSE file.
#
# Authors: Rafael Carrascosa <rcarrascosa@machinalis.com>
# Gonzalo Garcia Berrotaran <ggarcia@machinalis.com>

"""
Domain specific language for DBpedia quepy.
"""

from quepy.dsl import *

# Setup the Keywords for this application
HasKeyword.relation = "rdfs:label"
HasKeyword.language = "en"

# Setup Fixed Type
FixedType.fixedtyperelation = "rdf:type"


class IsPerson(FixedType):
    fixedtype = "foaf:Person"


class IsPolitician(FixedType):
    fixedtype = "dbpedia-owl:Politician"


class IsPlace(FixedType):
    fixedtype = "dbpedia:Place"


class IsCountry(FixedType):
    fixedtype = "dbpedia-owl:Country"


class IsPlace(FixedType):
    fixedtype = "dbpedia-owl:Place"


class IsBand(FixedType):
    fixedtype = "dbpedia-owl:Band"


class IsSong(FixedType):
    fixedtype = "dbpedia-owl:Song"


class IsAlbum(FixedType):
    fixedtype = "dbpedia-owl:Album"


class PartOfAlbum(FixedRelation):
    relation = "dbpedia-owl:album"


class IsTvShow(FixedType):
    fixedtype = "dbpedia-owl:TelevisionShow"


class IsMovie(FixedType):
    fixedtype = "dbpedia-owl:Film"


class IsMilitaryConflict(FixedType):
    fixedtype = "dbpedia-owl:MilitaryConflict"


class UsedInWar(FixedRelation):
    relation = "dbpedia-owl:usedInWar"


class HasNationality(FixedRelation):
    relation = "dbpedia-owl:nationality"


class HasAlmaMater(FixedRelation):
    relation = "dbpedia-owl:almaMater"


class UsedByCountry(FixedRelation):
    relation = "dbpprop:usedBy"


class PartOfBattle(FixedRelation):
    relation = "dbpedia-owl:battle"


class HasBirthPlace(FixedRelation):
    relation = "dbpedia-owl:birthPlace"


class HasDate(FixedRelation):
    relation = "dbpedia-owl:date"


class HasBirthDate(FixedRelation):
    relation = "dbpedia-owl:birthDate"


class ConflictLocation(FixedRelation):
    relation = "dbpedia-owl:place"


class HasShowName(FixedDataRelation):
    relation = "dbpprop:showName"
    language = "en"


class HasName(FixedDataRelation):
    relation = "dbpprop:name"
    language = "en"


class LabelOfFixedDataRelation(FixedDataRelation):
    relation = "rdfs:label"
    language = "en"


class LongName(FixedDataRelation):
    relation = "dbpedia-owl:longName"
    language = "en"


class DefinitionOf(FixedRelation):
    relation = "rdfs:comment"
    reverse = True


class LabelOf(FixedRelation):
    relation = "rdfs:label"
    reverse = True


class UTCof(FixedRelation):
    relation = "dbpprop:utcOffset"
    reverse = True


class PresidentOf(FixedRelation):
    relation = "dbpprop:leaderTitle"
    reverse = True


class IncumbentOf(FixedRelation):
    relation = "dbpprop:incumbent"
    reverse = True


class CapitalOf(FixedRelation):
    relation = "dbpedia-owl:capital"
    reverse = True


class LanguageOf(FixedRelation):
    relation = "dbpprop:officialLanguages"
    reverse = True


class PopulationOf(FixedRelation):
    relation = "dbpprop:populationCensus"
    reverse = True


class IsMemberOf(FixedRelation):
    relation = "dbpedia-owl:bandMember"
    reverse = True


class ActiveYears(FixedRelation):
    relation = "dbpprop:yearsActive"
    reverse = True


class MusicGenreOf(FixedRelation):
    relation = "dbpedia-owl:genre"
    reverse = True


class ArtistOf(FixedRelation):
    relation = "dbpedia-owl:artist"
    reverse = True


class ProducedBy(FixedRelation):
    relation = "dbpedia-owl:producer"


class BirthDateOf(FixedRelation):
    relation = "dbpprop:birthDate"
    reverse = True


class BirthPlaceOf(FixedRelation):
    relation = "dbpedia-owl:birthPlace"
    reverse = True


class ReleaseDateOf(FixedRelation):
    relation = "dbpedia-owl:releaseDate"
    reverse = True


class StarsIn(FixedRelation):
    relation = "dbpprop:starring"
    reverse = True


class NumberOfEpisodesIn(FixedRelation):
    relation = "dbpedia-owl:numberOfEpisodes"
    reverse = True


class ShowNameOf(FixedRelation):
    relation = "dbpprop:showName"
    reverse = True


class HasActor(FixedRelation):
    relation = "dbpprop:starring"


class CreatorOf(FixedRelation):
    relation = "dbpprop:creator"
    reverse = True


class NameOf(FixedRelation):
    relation = "foaf:name"
    # relation = "dbpprop:name"
    reverse = True


class DirectedBy(FixedRelation):
    relation = "dbpedia-owl:director"


class DirectorOf(FixedRelation):
    relation = "dbpedia-owl:director"
    reverse = True


class DurationOf(FixedRelation):
    # DBpedia throws an error if the relation it's
    # dbpedia-owl:Work/runtime so we expand the prefix
    # by giving the whole URL.
    relation = "<http://dbpedia.org/ontology/Work/runtime>"
    reverse = True


class HasAuthor(FixedRelation):
    relation = "dbpedia-owl:author"


class AuthorOf(FixedRelation):
    relation = "dbpedia-owl:author"
    reverse = True


class IsBook(FixedType):
    fixedtype = "dbpedia-owl:Book"


class LocationOf(FixedRelation):
    relation = "dbpedia-owl:location"
    reverse = True


class HasParents(FixedRelation):
    relation = "dbpprop:parents"
    reverse = True


class HasChild(FixedRelation):
    relation = "dbpprop:children"
    reverse = True


class HasImage(FixedDataRelation):
    relation = "dbpprop:hasPhotoCollection"


class ReturnValue(object):
    def __init__(self, i, j):
        self.i = i
        self.j = j