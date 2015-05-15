package com.infinity.config;

import com.infinity.data.jpa.domain.Candidate;
import com.infinity.data.jpa.domain.CandidateKnowTechno;
import com.infinity.data.jpa.domain.Experiences;
import com.infinity.data.jpa.domain.Technologies;
import com.infinity.data.jpa.domain.Users;
import com.infinity.data.jpa.service.CandidateKnowTechnoRepository;
import com.infinity.data.jpa.service.CandidateRepository;
import com.infinity.data.jpa.service.ExperiencesRepository;
import com.infinity.data.jpa.service.TechnologieRepository;
import com.infinity.data.jpa.service.UsersRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.infinity.controller"})
@EnableSpringDataWebSupport
@EnableJpaRepositories("com.infinity.data.jpa.service")
@PropertySource("classpath:application.properties")

public class MvcConfiguration extends WebMvcConfigurerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(MvcConfiguration.class);

    private static final List<String> DEFAULT_TILES_DEFINITIONS = new LinkedList<>();

    static {

        DEFAULT_TILES_DEFINITIONS.add("/WEB-INF/tiles.xml");
        DEFAULT_TILES_DEFINITIONS.add("/WEB-INF/view.xml");
    }

    @Value("${createData}")
    private String createData;

    @Autowired
    private UsersRepository repository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private TechnologieRepository technologieRepository;

    @Autowired
    private CandidateKnowTechnoRepository candidateKnowTechnoRepository;

    @Autowired
    private ExperiencesRepository experiencesRepository;

    @Bean
    public String setTestData() {

        String toto = "toto";

        Boolean res = createData.equals("true");
        LOG.debug(res.toString());
        if (res) {

            LOG.debug("DATA CREATION");
            LOG.debug("create data {}", createData);

            Users users = new Users();
            users.setName("nicolas");
            users.setPass("pass");
            users.setEmail("nicolas.canicatti@infinity-web.fr");
            repository.save(users);

            Users eliza = new Users();
            eliza.setName("eliza");
            eliza.setPass("titi");
            eliza.setEmail("eliza.swirtun@infinity-web.fr");
            repository.save(eliza);

            Calendar calendar = Calendar.getInstance();
            calendar.set(2000, 1, 1);
            Candidate c1 = new Candidate();
            c1.setName("Nicolas");
            c1.setAdress("20 rue jean andre");
            c1.setCity("nice");
            c1.setBirthDate(calendar.getTime());
            c1.setCreationdate(new Date());
            c1.setEmail("nicolas@gmail.com");
            c1.setTelephone("0630626334");
            candidateRepository.save(c1);

            calendar.set(1980, 12, 12);
            Candidate c2 = new Candidate();
            c2.setName("Mark Andre");
            c2.setAdress("20 rue jean Mark");
            c2.setCity("Nice");
            c2.setBirthDate(calendar.getTime());
            c2.setCreationdate(new Date());
            c2.setEmail("nicolasssss@gmail.com");
            c2.setTelephone("0662094137");
            candidateRepository.save(c2);

            Technologies java = new Technologies();
            java.setName("Java");

            technologieRepository.save(java);

            Technologies php = new Technologies();
            php.setName("PHP");

            technologieRepository.save(php);

            CandidateKnowTechno candidateKnowTechno = new CandidateKnowTechno();
            candidateKnowTechno.setCandidate(c2);
            candidateKnowTechno.setTechno(php);

            candidateKnowTechnoRepository.save(candidateKnowTechno);

            Experiences experiences = new Experiences();
            experiences.setTitle("Ingenieur dev");
            experiences.setCompagny("Orange");
            experiences.setDesccription("bal fdlmskgsdfùlkhgdlfkhg sghmlsdfkg mlsdfkhgmslk%KLFJHAZ%ELKFAZ E");

            calendar.set(2000, 1, 1);
            experiences.setStartDate(calendar.getTime());
            calendar.set(2002, 1, 1);
            experiences.setEndDate(calendar.getTime());
            experiences.setCandidate(c2);
            experiencesRepository.save(experiences);

            Experiences experiences2 = new Experiences();
            experiences2.setTitle("Ingenieur dev chef");
            experiences2.setCompagny("Orange");
            experiences2.setDesccription("bal fdlmskgsdfùlkhgdlfkhg sghmlsdfkg mlsdfkhgmslk%KLFJHAZ%ELKFAZ E");

            calendar.set(1998, 1, 1);
            experiences2.setStartDate(calendar.getTime());
            calendar.set(1999, 1, 1);
            experiences2.setEndDate(calendar.getTime());
            experiences2.setCandidate(c2);
            experiencesRepository.save(experiences2);

        }

        return toto;
    }
    
   
    
    @Bean
    public TilesViewResolver tilesViewResolver() {
        return new TilesViewResolver();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(name = "multipartResolver")
    public StandardServletMultipartResolver multipartResolver() {
        
        return new StandardServletMultipartResolver();
    }

    @Bean
    public TilesConfigurer tilesConfigurer() {

        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(DEFAULT_TILES_DEFINITIONS.toArray(new String[DEFAULT_TILES_DEFINITIONS.size()]));

        return tilesConfigurer;
    }
}
