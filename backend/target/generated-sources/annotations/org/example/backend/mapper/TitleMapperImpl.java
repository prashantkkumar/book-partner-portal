package org.example.backend.mapper;

import java.time.Instant;
import javax.annotation.processing.Generated;
import org.example.backend.dto.RoyschedDto;
import org.example.backend.dto.TitleDto;
import org.example.backend.entities.Roysched;
import org.example.backend.entities.Title;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-06T20:01:55+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.14 (JetBrains s.r.o.)"
)
@Component
public class TitleMapperImpl implements TitleMapper {

    @Override
    public Title toEntity(TitleDto titleDto) {
        if ( titleDto == null ) {
            return null;
        }

        Title title = new Title();

        title.setTitleId( titleDto.titleId() );
        title.setTitle( titleDto.title() );
        title.setType( titleDto.type() );
        title.setPrice( titleDto.price() );
        title.setAdvance( titleDto.advance() );
        title.setRoyalty( titleDto.royalty() );
        title.setYtdSales( titleDto.ytdSales() );
        title.setNotes( titleDto.notes() );
        title.setPubdate( titleDto.pubdate() );

        return title;
    }

    @Override
    public TitleDto toDto(Title title) {
        if ( title == null ) {
            return null;
        }

        String titleId = null;
        String title1 = null;
        String type = null;
        Double price = null;
        Double advance = null;
        Integer royalty = null;
        Integer ytdSales = null;
        String notes = null;
        Instant pubdate = null;

        titleId = title.getTitleId();
        title1 = title.getTitle();
        type = title.getType();
        price = title.getPrice();
        advance = title.getAdvance();
        royalty = title.getRoyalty();
        ytdSales = title.getYtdSales();
        notes = title.getNotes();
        pubdate = title.getPubdate();

        TitleDto titleDto = new TitleDto( titleId, title1, type, price, advance, royalty, ytdSales, notes, pubdate );

        return titleDto;
    }

    @Override
    public Title partialUpdate(TitleDto titleDto, Title title) {
        if ( titleDto == null ) {
            return title;
        }

        if ( titleDto.titleId() != null ) {
            title.setTitleId( titleDto.titleId() );
        }
        if ( titleDto.title() != null ) {
            title.setTitle( titleDto.title() );
        }
        if ( titleDto.type() != null ) {
            title.setType( titleDto.type() );
        }
        if ( titleDto.price() != null ) {
            title.setPrice( titleDto.price() );
        }
        if ( titleDto.advance() != null ) {
            title.setAdvance( titleDto.advance() );
        }
        if ( titleDto.royalty() != null ) {
            title.setRoyalty( titleDto.royalty() );
        }
        if ( titleDto.ytdSales() != null ) {
            title.setYtdSales( titleDto.ytdSales() );
        }
        if ( titleDto.notes() != null ) {
            title.setNotes( titleDto.notes() );
        }
        if ( titleDto.pubdate() != null ) {
            title.setPubdate( titleDto.pubdate() );
        }

        return title;
    }

    @Override
    public Roysched toEntity(RoyschedDto royschedDto) {
        if ( royschedDto == null ) {
            return null;
        }

        Roysched roysched = new Roysched();

        roysched.setId( royschedDto.id() );
        roysched.setLorange( royschedDto.lorange() );
        roysched.setHirange( royschedDto.hirange() );
        roysched.setRoyalty( royschedDto.royalty() );

        return roysched;
    }

    @Override
    public RoyschedDto toDto(Roysched roysched) {
        if ( roysched == null ) {
            return null;
        }

        Integer id = null;
        Integer lorange = null;
        Integer hirange = null;
        Integer royalty = null;

        id = roysched.getId();
        lorange = roysched.getLorange();
        hirange = roysched.getHirange();
        royalty = roysched.getRoyalty();

        RoyschedDto royschedDto = new RoyschedDto( id, lorange, hirange, royalty );

        return royschedDto;
    }

    @Override
    public Roysched partialUpdate(RoyschedDto royschedDto, Roysched roysched) {
        if ( royschedDto == null ) {
            return roysched;
        }

        if ( royschedDto.id() != null ) {
            roysched.setId( royschedDto.id() );
        }
        if ( royschedDto.lorange() != null ) {
            roysched.setLorange( royschedDto.lorange() );
        }
        if ( royschedDto.hirange() != null ) {
            roysched.setHirange( royschedDto.hirange() );
        }
        if ( royschedDto.royalty() != null ) {
            roysched.setRoyalty( royschedDto.royalty() );
        }

        return roysched;
    }
}
