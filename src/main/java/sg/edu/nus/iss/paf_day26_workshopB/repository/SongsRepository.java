package sg.edu.nus.iss.paf_day26_workshopB.repository;

import java.util.Collections;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class SongsRepository {

    @Autowired
    private MongoTemplate template;


    /*
     *  db.popular_songs.distinct("released_year")
     */
    public List<Integer> getYears() {

        List<Integer> results = template.findDistinct(new Query(), "released_year", "popular_songs", Integer.class);

        Collections.sort(results, Collections.reverseOrder());

        return results;

    }


    /*
     *  db.popular_songs.find({
     *      "released_year" : 'year'
     *  })
     *  .projection({"track_name": 1, "artist(s)_name" : 1, _id: 0})
     */
    public List<Document> findSongsByYear(int year) {
        
        Criteria criteria = Criteria.where("released_year")
                                    .is(year);

        Query query = Query.query(criteria);
        query.fields().include("track_name", "artist(s)_name")
                      .exclude("_id");

        List<Document> results = template.find(query, Document.class, "popular_songs");

        return results;

    }

}
