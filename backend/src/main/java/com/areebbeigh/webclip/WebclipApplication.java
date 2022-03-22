package com.areebbeigh.webclip;

import com.areebbeigh.webclip.respositories.PasteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories
@EnableMongoAuditing
@Slf4j
public class WebclipApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebclipApplication.class, args);
	}

	@Bean
	public CommandLineRunner test() {
		// Testing some stuff
		return (String... args) -> {
//			log.info(mongoTemplate.toString());
//			LookupOperation op = Aggregation.lookup(
//					"userEntity", "user", "_id", "user");
//			UnwindOperation op2 = Aggregation.unwind("user");
//			List<String> res = mongoTemplate.aggregate(
//					Aggregation.newAggregation(op, op2),
//					"pasteEntity",
//					String.class).getMappedResults();
//			List<PasteDto> res2 = mongoTemplate.aggregate(
//					Aggregation.newAggregation(op, op2),
//					"pasteEntity",
//					PasteDto.class).getMappedResults();
//			log.info(res.toString());
//			log.info(res2.toString());
//			log.info(converter.toString());
//			final BasicDBObject paste = new BasicDBObject();
//			final BasicDBObject user = new BasicDBObject();
//			user.put("username", "hi2");
//			paste.put("content", "hi");
//			paste.put("user", user);
//			final PasteEntity res = mongoTemplate.getConverter().read(PasteEntity.class, paste);
//			final SamplePasteDto res2 = mongoTemplate.getConverter().read(SamplePasteDto.class, paste);
//			log.info(res.toString());
//			log.info(res2.toString());
//			PasteEntity p = mongoTemplate.findOne(Query.query(Criteria.where("_id").is("6232f32c98c0f6719b775600")), PasteEntity.class);
//			log.info(p.toString());
//			Pageable page = PageRequest.of(0, 2);
//			var res = pasteRepository.findAllBy(page);
//			log.info(res.getContent().toString());
		};
	}
}
