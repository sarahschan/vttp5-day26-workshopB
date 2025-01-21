package sg.edu.nus.iss.paf_day26_workshopB.repository;

import java.util.Collections;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import static sg.edu.nus.iss.paf_day26_workshopB.repository.Constants.*;

@Repository
public class SongsRepository {

    @Autowired
    private MongoTemplate template;


    /*
     *  db.popular_songs.distinct("released_year")
     */
    public List<Integer> getYears() {

        List<Integer> results = template.findDistinct(new Query(), F_RELEASED_YEAR, C_POPULAR_SONGS, Integer.class);

        Collections.sort(results, Collections.reverseOrder());

        return results;

    }


    /*
     *  db.popular_songs.find({ "released_year" : 'year' })
     *  .projection({"track_name": 1, "artist(s)_name" : 1, _id: 0})
     *  .sort({ "track_name": 1, "artists(s)_name: 1"})
     */
    public List<Document> findSongsByYear(int year) {
        
        Criteria criteria = Criteria.where(F_RELEASED_YEAR)
                                    .is(year);

        Query query = Query.query(criteria)
                           .with(Sort.by(Sort.Direction.ASC, F_TRACK_NAME, F_ARTISTS_NAME));
                           
        query.fields().include(F_TRACK_NAME, F_ARTISTS_NAME)
                      .exclude("_id");

        List<Document> results = template.find(query, Document.class, C_POPULAR_SONGS);

        return results;

    }

}
